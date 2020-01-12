import React, {useState} from "react";
import {Button, Form, Modal} from "react-bootstrap";
import {postRequest} from "./SharedKernel/ApiRequest";
import {notify} from "react-notify-toast/bin/notify";

export default function NewAuthorButton(props) {
    const [show, showModal] = useState(false);
    const [name, changeName] = useState("");
    const [surname, changeSurname] = useState("");

    const handleClose = () => {
        changeName("");
        changeSurname("");
        showModal(false);
    };

    const addAuthor = () => {
        if (name.length < 1 || surname.length < 1) {
            notify.show("Uzupełnij wszystkie pola!", "error");
            return;
        }

        handleClose();

        postRequest('authors', {name: name, surname: surname}).then((response) => {
            props.refreshAuthors();
        });
    };

    return ([
        <a href="#" style={{cursor: "pointer"}} onClick={() => {
            showModal(true)
        }}>
            Dodaj nowego autora
        </a>,
        <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
                <Modal.Title>Nowa książka</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Form.Group controlId="formBasicName">
                        <Form.Label>Imie</Form.Label>
                        <Form.Control type="text" placeholder="Wpisz imię" value={name} onChange={(event) => {
                            changeName(event.target.value)
                        }}/>
                    </Form.Group>

                    <Form.Group controlId="formBasicSurnmae">
                        <Form.Label>Nazwisko</Form.Label>
                        <Form.Control type="text" placeholder="Wpisz nazwisko" value={surname} onChange={(event) => {
                            changeSurname(event.target.value)
                        }}/>
                    </Form.Group>
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={handleClose}>
                    Zamknij
                </Button>
                <Button variant="primary" onClick={addAuthor}>
                    Dodaj
                </Button>
            </Modal.Footer>
        </Modal>
    ]);
}