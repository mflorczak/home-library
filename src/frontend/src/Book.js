import {Button, Form, Modal, Table} from "react-bootstrap";
import React, {useState} from "react";
import {getRequest, removeRequest} from "./SharedKernel/ApiRequest";
import {notify} from "react-notify-toast/bin/notify";

export default function Book(props) {
    const [removed, remove] = useState(false);
    const [show, setShow] = useState(false);
    const [bookInfo, setBookInfo] = useState({author:{}});

    const removeBook = () => {
        removeRequest('/books/' + props.id).then(() => {
            remove(true);
            notify.show('Usunięto!');
        });
    };

    const handleClose = () => {
        setShow(false);
    };

    const getBookInfo = () => {
        getRequest('books/' + props.id).then((response) => {
            return response.json();
        }).then((response) => {
            setBookInfo(response);
        });
    };

    const showBookInfo = () => {
        setBookInfo({author:{}});
        setShow(true);
        getBookInfo();
    };

    if (removed) {
        return (<></>);
    }

    return (
        <tr key={props.id}>
            <td>{props.lp}</td>
            <td>
                <a href="#" onClick={showBookInfo}>
                    {props.title}
                </a>
                <Modal show={show} onHide={handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>{props.title}</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Table bordered>
                            <tbody>
                            <tr>
                                <td>Nazwa:</td>
                                <td>{bookInfo.title}</td>
                            </tr>
                            <tr>
                                <td>Wydawca:</td>
                                <td>{bookInfo.publisher}</td>
                            </tr>
                            <tr>
                                <td>Opis:</td>
                                <td>{bookInfo.description}</td>
                            </tr>
                            <tr>
                                <td>Autor:</td>
                                <td>{bookInfo.author.name} {bookInfo.author.surname}</td>
                            </tr>
                            </tbody>
                        </Table>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleClose}>
                            Zamknij
                        </Button>
                    </Modal.Footer>
                </Modal>
            </td>
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