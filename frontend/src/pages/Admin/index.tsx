import Navbar from "./Navbar";
import './styles.css';

const Admin = () => {

    return (
        <div className="admin-container">
            <Navbar />
            <div className="admin-content-container">
                <h1>Conteúdo</h1>
            </div>
        </div>
    );
}

export default Admin;