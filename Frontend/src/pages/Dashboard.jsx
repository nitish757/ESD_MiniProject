import { Outlet, useLocation, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";

const Dashboard = () => {
  const location = useLocation();
  const navigate= useNavigate();
  const email = location.state?.email;
  const [employee, setEmployee] = useState(null); // State for employee details

  useEffect(() => {
    const fetchEmployeeDetails = async () => {
      try {
        const token = JSON.parse(localStorage.getItem("jwtToken"));

        const response = await axios.get(`http://localhost:8080/dashboard/${email}`, {
          headers: {
            Authorization: `Bearer ${token}`, 
          },
        });

        setEmployee(response.data);
      } catch (error) {
        console.error("Error fetching employee details:", error);
      }
    };

    fetchEmployeeDetails();
  }, [email]);

  const handleClick = ()=>{
    navigate("/SalaryDetails", {state: {email:email}});
  }

  const handleClick2 = ()=>{
    navigate("/SalaryHistory", {state: {email: email}});
  }

  if (!employee) {
    return <div className="p-4">Loading employee details...</div>;
  }

  return (
    <div className="container-fluid">
      <div className="row">
        {/* Left Sidebar */}
        <div className="col-md-3 bg-light border-end vh-100 p-4">
          <h4 className="mb-4">Employee Details</h4>
          <p><strong>Name:</strong> {employee.firstName} {employee.lastName}</p>
          <p><strong>Role:</strong> {employee.title}</p>
          <p><strong>Department:</strong> {employee.department}</p>
          <p><strong>Email:</strong> {employee.email}</p>
        </div>

        {/* Middle Section */}
        <div className="col-md-9 p-4">
          <h2 className="mb-4">Dashboard</h2>
          <ul className="list-group">
            <li onClick={handleClick} className="list-group-item">
            View Salary
               {/* to="/SalaryDetails" className="text-decoration-none">View Salary</Link> */}
            </li>
            <li onClick={handleClick2} className="list-group-item">View Salary History
              {/* <Link to="view-salary-history" className="text-decoration-none">View Salary History</Link> */}
            </li>
            {/*<li className="list-group-item">
              <Link to="download-payslip" className="text-decoration-none">Download Payslip</Link>
            </li> */}
          </ul>

          {/* Outlet for child routes */}
          <div className="mt-4">
            <Outlet />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
