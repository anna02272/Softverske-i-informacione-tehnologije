import React from 'react';
import {
  AsyncStorage,
  Button,
  View,
} from 'react-native';
import Axios from '../util/axios/axios';
import { Input } from 'react-native-elements'

export default class LoginScreen extends React.Component<{navigation:any},{username:string, password:string}>{
      
    constructor(props){
        super(props);
        this.state={username:'', password:''};
    }

      static navigationOptions = {
        title: 'Please sign in',
      };
    
      usernameChange = (e) => {
        console.log(e.nativeEvent.text);
        this.setState({username:e.nativeEvent.text});
      };

      passwordChange = (e) => {
        console.log(e.nativeEvent.text);
        this.setState({password:e.nativeEvent.text});
      };

      render() {
        return (
          <View>
            <Input placeholder="username" onChange={this.usernameChange}></Input>
            <Input placeholder="password" onChange={this.passwordChange} secureTextEntry={true}></Input>
            <Button title="Sign in!" onPress={this.signInAsync} />
          </View>
        );
      }
    
      signInAsync = async () => {
        Axios.post('/api/users/authenticate',{name:this.state.username, password:this.state.password}).then((res)=>{
            console.log(res.data);
            let token = res && res.data['token'];
            if (token) {
              AsyncStorage.setItem('userToken', token).then(() => {
                this.props.navigation.navigate('App');
              });
            }
            else {
                this.props.navigation.navigate('Auth');
            }
        });
        this.props.navigation.navigate('App');
      };

}