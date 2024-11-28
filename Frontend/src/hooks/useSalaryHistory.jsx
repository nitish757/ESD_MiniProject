import { useState, useEffect } from "react";
import fetchData from "../utils/network";
import  { jwtDecode } from "jwt-decode";

const useSalariHistory= ()=>{
    const [salaryHistory, setSalaryHistory] = useState([]); 

    useEffect(() => {
    // if (!email) {
    //   console.error("Email is not available!");
    //   return;
    // }

    const fetchSalaryHistory = async () => {
      try {
        const token = localStorage.getItem("jwtToken");
        // const token = JSON.parse(localStorage.getItem("jwtToken"));
        const decoded = jwtDecode(token);
        const email = decoded.sub;
        const data = await fetchData("salaryHistory", email); 

        setSalaryHistory(data); 
      } catch (err) {
        console.error("Error fetching salary history:", err);
      }
    };

    fetchSalaryHistory();
  }, []);
  return salaryHistory;
};

export default useSalariHistory;
