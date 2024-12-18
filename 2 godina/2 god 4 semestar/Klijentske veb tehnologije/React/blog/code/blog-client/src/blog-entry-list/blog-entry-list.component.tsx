import React from 'react';
import BlogEntry from '../blog-entry/blog-entry.component';
import { Link } from 'react-router-dom';

export function BlogEntryList (props: {entries:any[], isLoggedIn:boolean}) {

    const entries = props.entries.map(x =>  
             (<li key={x._id}>
                <BlogEntry entry={x}></BlogEntry>
                { props.isLoggedIn ? <Link to={`/entries/${x._id}`}><button className="btn btn-default">details</button></Link> : null }
                { props.isLoggedIn ? <button className="btn btn-danger">delete</button> : null }
            </li>));
        return (
            <ul className="list-unstyled">
                {entries}
            </ul>
        )
}
