import React from 'react';
import {View, Text, FlatList, AsyncStorage, Button} from 'react-native';
import Axios from '../util/axios/axios';


export default class BlogEntriesComponent extends React.Component<{navigation:any},{entries: any[]}> {
    constructor(props){
        super(props);
        this.state = {entries:[]};
    }

    loadDate() {
        Axios.get('/api/blogEntries').then(resp => {
            console.log(resp.data);
            this.setState({entries: resp.data});
        })
    }

    componentDidMount () {
        AsyncStorage.getItem('userToken').then(token => {
            if(token){
                this.loadDate() 
            }
            else{
                this.props.navigation.navigate('Auth');
            }
        });

    }

    _logout = async () => {
        try {
            console.log('sign out');
            await AsyncStorage.removeItem('userToken');
            this.props.navigation.navigate('Auth');
            return true;
          }
          catch(exception) {
            return false;
          }
    };

    render(){
        const entries = this.state.entries.map(entry => { return {key: entry["title"]} });
        return <View>
            <Text>blog entries</Text>
            <FlatList data={entries}
                renderItem={({item}) => <Text>{item.key}</Text>}
            ></FlatList>
            <Button onPress={this._logout} title="log out"/>
        </View>
    }
}