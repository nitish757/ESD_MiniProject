import { useState, useEffect } from "react";
import fetchData from "../utils/network";
import  { jwtDecode } from "jwt-decode";

const useSalaryDetails = () => {
  const [salaries, setSalaries] = useState([]);

  useEffect(() => {
    const fetchSalaryDetails = async () => {
      try {
        const token = localStorage.getItem("jwtToken");
        // const token = JSON.parse(localStorage.getItem("jwtToken"));
        const decoded = jwtDecode(token);
        const email = decoded.sub;
        const data = await fetchData("salary", email); 
        setSalaries(data);
      } catch (err) {
        console.error("Error fetching salary details:", err);
      }
    };

    // if (email) {
      fetchSalaryDetails();
    // }
  }, []);

  return salaries;
};

export default useSalaryDetails;
