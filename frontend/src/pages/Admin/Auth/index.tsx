import {ReactComponent as AuthImage} from 'assets/images/card-login.svg';
import { Outlet } from 'react-router-dom';
import './styles.css';

const Auth = () => {
    return (
        <div className="auth-container">
            <div className="auth-banner-container">
                <h1>
                    Divulgue seus produtos no DS Catalog
                </h1>
                <p>
                    Faça parte do nosso catálogo  de divulgação e aumente a venda dos seus produtos.
                </p>
                <AuthImage />
            </div>
            <div className='auth-form-container'>
                <Outlet />
            </div>
        </div>
    );
}

export default Auth;