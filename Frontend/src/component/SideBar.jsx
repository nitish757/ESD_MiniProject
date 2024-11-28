import PropTypes from "prop-types";
import { useNavigate } from "react-router-dom";

const SideBar = ({ firstName, lastName, title, department, email }) => {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("jwtToken");
    navigate("/");
  };
  return (
    <div className="col-md-3 bg-light border-end vh-100 p-4 d-flex flex-column">
      <div className="d-flex flex-column align-items-start vh-25 px-3 pt-4">
        <h4 className="text-primary font-weight-bold mb-5 fs-2">Employee Details</h4>
        <p className="fs-4 mb-3">
          <strong>Name:</strong> {firstName} {lastName}
        </p>
        <p className="fs-4 mb-3">
          <strong>Role:</strong> {title}
        </p>
        <p className="fs-4 mb-3">
          <strong>Department:</strong> {department}
        </p>
        <p className="fs-4 mb-3">
          <strong>Email:</strong>{" "}
          <a href={`mailto:${email}`} className="text-decoration-none">
            {email}
          </a>
        </p>
      </div>

      <div className="mt-auto">
        <button
          onClick={handleLogout}
          className="btn btn-outline-danger w-100 mt-2"
        >
          Logout
        </button>
      </div>
    </div>
  );
};

SideBar.propTypes = {
  firstName: PropTypes.string.isRequired,
  lastName: PropTypes.string.isRequired,
  title: PropTypes.string.isRequired,
  department: PropTypes.string.isRequired,
  email: PropTypes.string.isRequired,
};

export default SideBar;
