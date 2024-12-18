import React from 'react';

export default function BlogEntry(props: {entry:any}){
    return (
        <div className="row">
            <div className="col-md-8">
                <h3 className="title"> 
                    {props.entry.title}
                </h3>
                <div>{props.entry.createdAt}</div>
                <div>{props.entry.updatedAt}</div>
                <div className="entry">{props.entry.entry}</div>
                <div>app-coment-list</div>
            </div>
            <div className="col-md-4">
                <div>app-comment-form</div>
            </div>
        </div>
    )
}