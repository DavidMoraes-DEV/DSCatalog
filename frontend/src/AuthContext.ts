import { createContext } from "react";
import { TokenData } from "util/auth";

export type AuthContextData = {
    authenticated: boolean;
    tokenData?: TokenData;
};
  
export type AuthContextType = {
    authContextData: AuthContextData,
    setAuthContextData: (AuthContextData: AuthContextData) => void;
}

//Cria o contexto Global
export const AuthContext = createContext<AuthContextType>({
    authContextData: {
        authenticated: false
    },
    setAuthContextData: () => null,
});