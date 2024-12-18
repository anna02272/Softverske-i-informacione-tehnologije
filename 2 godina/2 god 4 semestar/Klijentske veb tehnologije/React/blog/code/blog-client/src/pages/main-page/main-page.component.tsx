import React from 'react';
import { BlogEntryList } from '../../blog-entry-list/blog-entry-list.component';
import axios from '../../util/axios/axios';


export class MainPage extends React.Component<{isLoggedIn: boolean}, {entries:any[]}>{

    constructor(props: any){
        super(props);
        this.state = {entries:[]};
    }

    loadDate() {
        axios.get('/api/blogEntries').then(resp => {
            this.setState({entries: resp.data});
        })
    }

    componentDidMount () {
        this.loadDate()
    }

    render () {
        return (
            <div className="row">
                <div className="col-md-8">
                    <BlogEntryList entries={this.state.entries} isLoggedIn={this.props.isLoggedIn}></BlogEntryList>
                </div>
                <div className="col-md-4">
                    <div>search entries</div>
                    <div>add entry form</div>
                </div>
            </div>
        )
    }
}