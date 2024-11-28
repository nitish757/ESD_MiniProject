import { useState, useEffect } from "react";
import fetchData from "../utils/network"; 
import  { jwtDecode } from "jwt-decode";

const useEmployeeDetails = () => {
  const [employee, setEmployee] = useState(null);

  useEffect(() => {
    const fetchDetails = async () => {
      try {
        const token = localStorage.getItem("jwtToken");
        const decoded = jwtDecode(token);
        const email = decoded.sub;
        const data = await fetchData("dashboard", email); 
        setEmployee(data);
      } catch (error) {
        console.error("Error loading employee details:", error);
      }
    };

    // if (email) {
      fetchDetails();
    // }
  }, []);

  return employee;
};

export default useEmployeeDetails;
