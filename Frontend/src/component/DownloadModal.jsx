import { useState } from "react";
import { Modal, Button, Select, Spin } from "antd";
import fetchData from "../utils/network";
import PropTypes from "prop-types";
import  { jwtDecode } from "jwt-decode";

const DownloadModal = ({ visible, onClose}) => {
  const [selectedMonth, setSelectedMonth] = useState(""); // Selected month for download
  const [errorMessage, setErrorMessage] = useState(""); // Error message for invalid month
  const [loading, setLoading] = useState(false); // Loading state for download

  const months = [
    { name: "January", value: 1 },
    { name: "February", value: 2 },
    { name: "March", value: 3 },
    { name: "April", value: 4 },
    { name: "May", value: 5 },
    { name: "June", value: 6 },
    { name: "July", value: 7 },
    { name: "August", value: 8 },
    { name: "September", value: 9 },
    { name: "October", value: 10 },
    { name: "November", value: 11 },
    { name: "December", value: 12 },
  ];

  const handleDownload = async () => {
    if (!selectedMonth) {
      setErrorMessage("Please select a month!");
      return;
    }

    setLoading(true);
    try {
      const token = localStorage.getItem("jwtToken"); 
      const decoded = jwtDecode(token);
      const email = decoded.sub;
      const data = await fetchData("salary", email);

      const url = window.URL.createObjectURL(new Blob([data]));
      const link = document.createElement("a");
      link.href = url;
      link.setAttribute("download", `SalarySlip-${selectedMonth}.pdf`);
      document.body.appendChild(link);
      link.click();

      onClose(); 
      setLoading(false); 
    } catch (error) {
      console.error("Error downloading PDF:", error);
      setErrorMessage("Failed to download the PDF. Please try again.");
      setLoading(false); 
    }
  };

  return (
    <Modal
      title="Download Monthly Salary Slip"
      open={visible}
      onCancel={onClose} 
      footer={null}
      width={500}
    >
      <div className="form-group mt-3">
        <label htmlFor="month">Select Month:</label>
        <Select
          id="month"
          className="form-control"
          value={selectedMonth}
          onChange={setSelectedMonth}
          style={{ width: '100%', height: '38px'}}
        >
          <Select.Option value="">--Select a Month--</Select.Option>
          {months.map((month) => (
            <Select.Option key={month.value} value={month.value}>
              {month.name}
            </Select.Option>
          ))}
        </Select>
      </div>
      {errorMessage && <p className="text-danger">{errorMessage}</p>}
      <div className="text-center mt-3">
        <Button
          type="primary"
          onClick={handleDownload}
          loading={loading}
          disabled={!selectedMonth}
        >
          {loading ? <Spin /> : "Download PDF"}
        </Button>
      </div>
    </Modal>
  );
};

export default DownloadModal;

DownloadModal.propTypes = {
    visible: PropTypes.bool.isRequired,
    onClose: PropTypes.func.isRequired,
  };