import { useState, useEffect } from "react";
import axios from "axios";

const useSalaryDetails = (email) => {
  const [salaries, setSalaries] = useState([]);

  useEffect(() => {
    const fetchSalaryDetails = async () => {
      try {
        const token = JSON.parse(localStorage.getItem("jwtToken"));
        const response = await axios.get(`http://localhost:8080/salary/${email}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setSalaries(response.data);
      } catch (err) {
        console.error("Error fetching salary details:", err);
      }
    };

    if (email) {
      fetchSalaryDetails();
    }
  }, [email]);

  return salaries;
};

export default useSalaryDetails;
