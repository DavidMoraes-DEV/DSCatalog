import jwtDecode from 'jwt-decode';
import { getAuthData } from './storage';

export type Role = 'ROLE_OPERATION' | 'ROLE_ADMIN';

export type TokenData = {
  exp: number;
  user_name: string;
  authorities: Role[];
};

export const getTokenData = (): TokenData | undefined => {
  try {
    return jwtDecode(getAuthData().access_token) as TokenData;
  } catch (error) {
    return undefined;
  }
};

export const isAuthenticated = (): boolean => {
  const tokenData = getTokenData();
  return tokenData && tokenData.exp * 1000 > Date.now() ? true : false;
};

export const hasAnyRoles = (roles: Role[]): boolean => {
  if (roles.length === 0) {
    return true;
  }

  const tokenData = getTokenData();

  /*Forma didática para melhor compreensão
    if (tokenData !== undefined) {
      for (var i = 0; i< roles.length; i++) {
        if(tokenData.authorities.includes(roles[i])) {
          return true;
        }
      }
    }*/

  //Forma mais profisional do que utilizar o for acima
  if (tokenData !== undefined) {
    return roles.some((role) => tokenData.authorities.includes(role));
  }

  return false;
};
