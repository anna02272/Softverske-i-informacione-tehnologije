class JwtUtils {

    getRoles(token: string) {
      let jwtData = token.split('.')[1];
      let decodedJwtJsonData = window.atob(jwtData);
      let decodedJwtData = JSON.parse(decodedJwtJsonData);
  
      return [decodedJwtData.role];
    }
  
  }

const instance = new JwtUtils();

export default instance;
  