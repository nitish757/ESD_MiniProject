import { useEffect, useState } from "react";
import axios from "axios";
import { useLocation } from "react-router-dom";

const SalaryDetails = () => {
  const location = useLocation();
  const email = location.state?.email;
  const [salaries, setSalaries] = useState([]); // Updated to handle a list of salaries

  useEffect(() => {
    if (!email) {
      console.error("Email is not available!");
      return;
    }
    const fetchSalaryDetails = async () => {
      try {
        const token = JSON.parse(localStorage.getItem("jwtToken"));

        const response = await axios.get(`http://localhost:8080/salary/${email}`, {
          headers: {
            Authorization: `Bearer ${token}`,  
          },
        });

        setSalaries(response.data); // Set the list of salaries
        console.log(response.data);
      } catch (err) {
        console.error("Error fetching salary details:", err);
        if (err.response) {
          console.error("Response error:", err.response.data);
        }
      }
    };

    fetchSalaryDetails();
  }, [email]);

  return (
    <div className="container">
      <h2>Salary Details</h2>
      {salaries.length > 0 ? (
        <table className="table table-bordered table-striped">
          <thead>
            <tr>
              <th>Payment Date</th>
              <th>Description</th>
              <th>Salary</th>
            </tr>
          </thead>
          <tbody>
            {salaries.map((salary, index) => (
              <tr key={index}>
                <td>{salary.paymentDate}</td>
                <td>{salary.description}</td>
                <td>{salary.salary}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No salary details available.</p>
      )}
    </div>
  );
};

export default SalaryDetails;
