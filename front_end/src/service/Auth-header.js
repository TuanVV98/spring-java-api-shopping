export default function authHeader() {
    const user = JSON.parse(localStorage.getItem('access_token'));
  
    // console.log(user);
    if (user && user.token ) {
    //   console.log("True true")
      // console.log(user.token)
      return {"Authorization" : `Bearer ${user.token}`};
    } else {
      return {};
    } 
  }