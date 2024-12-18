import { StyleSheet } from 'react-native';
import { createAppContainer, createSwitchNavigator  } from 'react-navigation';
import { createStackNavigator } from 'react-navigation-stack';
import LoginScreen from './app/login/login.component';
import BlogEntriesComponent from './app/entries/blogEntries.component';
import AuthLoadingScreen from './app/login/authLoading.component';


const AppStack = createStackNavigator({
	Home: {
	  screen: BlogEntriesComponent,
	},
  });

const AuthStack = createStackNavigator({
	Login: {
		screen: LoginScreen,
	},
});
  
export default createAppContainer( createSwitchNavigator(
    {
      AuthLoading: AuthLoadingScreen,
      App: AppStack,
      Auth: AuthStack,
    },
    {
      initialRouteName: 'AuthLoading',
    }
  ));

const styles = StyleSheet.create({
	container: {
		flex: 1,
		justifyContent: 'center',
		alignItems: 'center',
		backgroundColor: '#F5FCFF'
	},
	instructions: {
		textAlign: 'center',
		color: '#333333',
		marginBottom: 5
	}
});