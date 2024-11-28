import { useState, useEffect } from "react";
import fetchData from "../utils/network"; 

const useEmployeeDetails = (email) => {
  const [employee, setEmployee] = useState(null);

  useEffect(() => {
    const fetchDetails = async () => {
      try {
        const data = await fetchData("dashboard", email); 
        setEmployee(data);
      } catch (error) {
        console.error("Error loading employee details:", error);
      }
    };

    if (email) {
      fetchDetails();
    }
  }, [email]);

  return employee;
};

export default useEmployeeDetails;
