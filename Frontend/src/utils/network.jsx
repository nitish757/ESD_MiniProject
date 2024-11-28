
import axios from "axios";

const fetchData = async (path, email) => {
  try {
    const token = JSON.parse(localStorage.getItem("jwtToken"));
    const response = await axios.get(`http://localhost:8080/${path}/${email}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data; 
  } catch (error) {
    console.error(`Error fetching data from ${path}:`, error);
    throw error; 
  }
};

export default fetchData;