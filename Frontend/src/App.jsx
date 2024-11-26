
// import { useState } from 'react'
// import NavBar from './components/NavBar'
import "bootstrap/dist/css/bootstrap.min.css";
import './App.css'
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Login from './pages/Login'
import Dashboard from "./pages/Dashboard";
import SalaryDetails from "./pages/SalaryDetails";
import SalaryHistory from "./pages/SalaryHistory";

const router = createBrowserRouter([
	{
		path: "/",
		element: <Login />
	},
	{
		path: "/Dashboard",
		element: <Dashboard/>
	},
	{
		path: "/SalaryDetails",
		element: <SalaryDetails />
	},
	{
		path: "/SalaryHistory",
		element: <SalaryHistory />
	},
	// {
	// 	path: "/view-salary-history",
	// 	element: <ViewSalaryHistory />
	// },
	// {

	// 	path: "/download-payslip",
	// 	element: <DownloadPayslip />
	// }
]);

function App() {
	return (
		<div className="App">
			<RouterProvider router={router} />
		</div>
	);
}

export default App
