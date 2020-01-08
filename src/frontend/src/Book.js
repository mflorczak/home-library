import {Button} from "react-bootstrap";
import React, {useState} from "react";
import {removeRequest} from "./SharedKernel/ApiRequest";
import {notify} from "react-notify-toast/bin/notify";

export default function Book(props) {
    const [removed, remove] = useState(false);

    const removeBook = () => {
        removeRequest('/books/' + props.id).then(() => {
            remove(true);
            notify.show('Usunięto!');
        });
    };

    if (removed) {
        return (<></>);
    }

    return (
        <tr key={props.id}>
            <td>{props.lp}</td>
            <td>{props.title}</td>
            <td>{props.authorName} {props.authorSurname}</td>
            <td>
                <Button
                    size={"sm"}
                    variant={"danger"}
                    onClick={removeBook}
                >
                    Usuń
                </Button>
            </td>
        </tr>
    );
}