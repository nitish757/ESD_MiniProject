import { useState, useEffect } from "react";
import axios from "axios";

const useSalariHistory= (email)=>{
    const [salaryHistory, setSalaryHistory] = useState([]); 

    useEffect(() => {
    if (!email) {
      console.error("Email is not available!");
      return;
    }

    const fetchSalaryHistory = async () => {
      try {
        const token = JSON.parse(localStorage.getItem("jwtToken"));

        const response = await axios.get(`http://localhost:8080/salaryHistory/${email}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        setSalaryHistory(response.data); 
      } catch (err) {
        console.error("Error fetching salary history:", err);
      }
    };

    fetchSalaryHistory();
  }, [email]);
  return salaryHistory;
};

export default useSalariHistory;
