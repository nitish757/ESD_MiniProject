import { Modal, Button } from "antd";
import PropTypes from "prop-types";

const SalaryModal = ({ visible, onClose, salaries }) => {
  return (
    <Modal
      title="Salary Details"
      open={visible}
      onCancel={onClose}
      footer={[
        <Button key="back" onClick={onClose}>
          Close
        </Button>,
      ]}
    >
      {salaries.length > 0 ? (
        <table className="employee-table table-bordered table-striped">
          <thead>
            <tr className="bg-primary text-white">
              <th>Payment Date</th>
              <th>Description</th>
              <th>Salary</th>
            </tr>
          </thead>
          <tbody>
            {salaries.map((salary, index) => (
              <tr
                key={index}
                style={{
                  backgroundColor: index % 2 === 0 ? "#ffffff" : "#daecfc",
                }}
              >
                <td>{salary.paymentDate}</td>
                <td>{salary.description}</td>
                <td>₹{salary.salary}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No salary details available.</p>
      )}
    </Modal>
  );
};

SalaryModal.propTypes = {
  visible: PropTypes.bool.isRequired,
  onClose: PropTypes.func.isRequired,
  salaries: PropTypes.array.isRequired,
};

export default SalaryModal;
