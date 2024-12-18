import React from "react";
import {
    Router,
    Switch,
    Route,
} from "react-router-dom";
import { MainPage } from "../pages/main-page/main-page.component";
import Login from "../pages/login/login.component";
import axios from '../util/axios/axios';
import jwtUtils from '../util/jwt/jwtUtils';
import history from '../util/history/history';
import { BlogEntryDetails } from "../pages/blog-entry-details/blog-entry-details.component";

export default class App extends React.Component<{}, {isLoggedIn:boolean}> {
  
    constructor(props: any){
        super(props);
        this.state={isLoggedIn:false};
    }

    componentDidMount(){
        if(localStorage.getItem('token')!==undefined){
            this.setState({isLoggedIn:true});
        }
        else{
            this.setState({isLoggedIn:false});
        }
    }

  render(){
    return (
    <Router history={history}>
      <div>
        {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL. */}
        <Switch>
          <Route path="/main">
            <MainPage isLoggedIn={this.state.isLoggedIn} />
          </Route>
          <Route path="/login">
            <Login onLogin={this.login} />
          </Route>
          <Route path="/entries/:entryId" component={BlogEntryDetails}/>
          <Route path="/">
            <MainPage isLoggedIn={this.state.isLoggedIn} />
          </Route>
        </Switch>
      </div>
    </Router>
  );
  }

  private login = (username: string, password: string) => {
    axios.post('/api/users/authenticate',{name:username, password:password}).then((res)=>{
        let token = res && res.data['token'];
        if (token) {
          localStorage.setItem('currentUser', JSON.stringify({
            username: username,
            roles: jwtUtils.getRoles(token),
            token: token.split(' ')[1]
          }));
          this.setState({isLoggedIn:true});
          //redirekcija
          history.push('/main');
        }
        else {
            this.setState({isLoggedIn:false});
        }
    });
  }
}

