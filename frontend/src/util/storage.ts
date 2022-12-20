import { PasswordResetToken } from "pages/Admin/Auth/CardRecover";

const tokenKey = 'authData';
const tokenResetPassword = 'token'

type LoginResponse = {
  access_token: string;
  token_type: string;
  expires_in: number;
  scope: string;
  userFirtName: string;
  userId: number;
};

export const saveAuthData = (obj: LoginResponse) => {
  localStorage.setItem(tokenKey, JSON.stringify(obj));
};

export const getAuthData = () => {
  const str = localStorage.getItem(tokenKey) ?? '{}';
  const obj = JSON.parse(str) as LoginResponse;

  return obj;
};

export const removeAuthData = () => {
  localStorage.removeItem(tokenKey);
};

export const savePasswordResetTokenLocalStorage = (obj: PasswordResetToken) => {
  localStorage.setItem(tokenResetPassword, JSON.stringify(obj));
}

export const getPasswordResetTokenLocalStorage = () => {
  const str = localStorage.getItem(tokenResetPassword) ?? '{}';
  const obj = JSON.parse(str);

  return obj;
};

export const removePasswordResetTokenLocalStorage = () => {
  localStorage.removeItem(tokenResetPassword);
};