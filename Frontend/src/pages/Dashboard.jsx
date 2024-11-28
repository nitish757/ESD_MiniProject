import { useState } from "react";
// import { useLocation } from "react-router-dom";
import SideBar from "../component/SideBar";
import SalaryModal from "../component/SalaryModal";
import useEmployeeDetails from "../hooks/useEmployeeDetails";
import useSalaryDetails from "../hooks/useSalaryDetails";
import useSalaryHistory from "../hooks/useSalaryHistory";
import DownloadModal from "../component/DownloadModal"; 

const Dashboard = () => {
  // const location = useLocation();
  // const email = location.state?.email;

  const [modalVisible, setModalVisible] = useState(false); 
  const [mVisible2, setMVisible2] = useState(false); 
  const [downloadModalVisible, setDownloadModalVisible] = useState(false); 

  const employee = useEmployeeDetails();
  const salaries = useSalaryDetails();
  const salaryHistory = useSalaryHistory();

  const handleSalary = () => {
    setModalVisible(true);
  };

  const handleSalaryHistory = () => {
    setMVisible2(true);
  };


  const handleDownload = () => {
    setDownloadModalVisible(true);
  };

  if (!employee) {
    return <div className="p-4">Loading employee details...</div>;
  }

  return (
    <div className="container-fluid">
      <h2 className="mb-4 text-white bg-primary p-4 employee-dashboard-header">
        Employee Dashboard
      </h2>
      <div className="row">
        <SideBar
          firstName={employee.firstName}
          lastName={employee.lastName}
          title={employee.title}
          department={employee.department}
          email={employee.email}
        />
        <div className="col-md-9 p-5">
          <div className="d-flex justify-content-center align-items-center vh-100">
            <div className="card shadow-lg" style={{ width: "50rem", padding: "2rem" }}>
              <div className="card-body">
                <h5 className="card-title">
                  Welcome, {employee.firstName} {employee.lastName}
                </h5>
                <p className="card-text">
                  View your salary, download your payslips, or access your salary disbursement history.
                </p>

                <ul className="list-group list-group-flush">
                  <li
                    onClick={handleSalary}
                    className="list-group-item list-group-item-action d-flex justify-content-between align-items-center"
                    style={{ cursor: 'pointer'}}
                  >
                    <span className="text-success">View Current Salary</span>
                  </li>
                  <li
                    onClick={handleSalaryHistory}
                    className="list-group-item list-group-item-action d-flex justify-content-between align-items-center"
                    style={{ cursor: 'pointer'}}
                  >
                    <span className="text-warning">View Salary Disbursement History</span>
                  </li>
                  <li
                    onClick={handleDownload} 
                    className="list-group-item list-group-item-action d-flex justify-content-between align-items-center"
                    style={{ cursor: 'pointer'}}
                  >
                    <span className="text-danger">Download Payslip</span>
                  </li>
                </ul>
              </div>
            </div>
          </div>

          <SalaryModal
            visible={modalVisible}
            onClose={()=> setModalVisible(false)}
            salaries={salaries}
          />

          <SalaryModal
            visible={mVisible2}
            onClose={()=> setMVisible2(false)}
            salaries={salaryHistory}
          />

          <DownloadModal
            visible={downloadModalVisible}
            onClose={() => setDownloadModalVisible(false)} 
          />
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
