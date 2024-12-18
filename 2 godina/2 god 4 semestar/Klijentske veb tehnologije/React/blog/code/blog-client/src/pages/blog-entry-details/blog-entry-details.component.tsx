import React from 'react';
import axios from '../../util/axios/axios';
import { RouteComponentProps } from 'react-router';

export class BlogEntryDetails extends React.Component<RouteComponentProps<{entryId:string}>,{entry:{title:any; createdAt:any; updatedAt:any; entry: any}}> {

    constructor(props: any) {
        super(props);
        this.state =   {entry:{
                            title:null,
                            createdAt:null,
                            updatedAt:null,
                            entry:null
                       }};
    }

    loadData() {
        const id = this.props.match.params['entryId'];
        axios.get(`/api/blogEntries/${id}`).then((resp)=>{
            this.setState({entry:resp.data});
        });
    }

    componentDidMount() {
        this.loadData();
    }  

    render(){
        return (
            <div className="row">
            <div className="col-md-8">
                <h3 className="title"> 
                    {this.state.entry.title}
                </h3>
                <div>{this.state.entry.createdAt}</div>
                <div>{this.state.entry.updatedAt}</div>
                <div className="entry">{this.state.entry.entry}</div>
                <div>app-coment-list</div>
            </div>
            <div className="col-md-4">
                <div>app-comment-form</div>
            </div>
            </div>
        );
    }
}