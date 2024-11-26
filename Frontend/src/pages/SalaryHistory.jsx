import { useEffect, useState } from "react";
import axios from "axios";
import { useLocation } from "react-router-dom";

const SalaryHistory = () => {
  const location = useLocation();
  const email = location.state?.email; // Retrieve email from route state
  const [salaryHistory, setSalaryHistory] = useState([]); // State to store salary history
  const [error, setError] = useState(null); // State to store errors

  useEffect(() => {
    if (!email) {
      console.error("Email is not available!");
      return;
    }

    const fetchSalaryHistory = async () => {
      try {
        const token = JSON.parse(localStorage.getItem("jwtToken"));

        // Fetch salary history data from the backend
        const response = await axios.get(`http://localhost:8080/salaryHistory/${email}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        setSalaryHistory(response.data); // Set data to state
      } catch (err) {
        console.error("Error fetching salary history:", err);
        setError("Failed to load salary history. Please try again.");
      }
    };

    fetchSalaryHistory();
  }, [email]);

  return (
    <div className="container">
      <h2 className="mt-4">Salary History</h2>
      {error ? (
        <p className="text-danger">{error}</p>
      ) : salaryHistory.length > 0 ? (
        <table className="table table-striped mt-3">
          <thead>
            <tr>
              <th>Payment Date</th>
              <th>Amount</th>
              <th>Description</th>
            </tr>
          </thead>
          <tbody>
            {salaryHistory.map((record, index) => (
              <tr key={index}>
                <td>{new Date(record.paymentDate).toLocaleDateString()}</td>
                <td>{record.salary}</td>
                <td>{record.description}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p className="mt-3">No salary history available.</p>
      )}
    </div>
  );
};

export default SalaryHistory;
