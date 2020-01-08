import {Button, Modal} from "react-bootstrap";
import React, {useState} from "react";

export default function NewBook() {
    const [show, showModal] = useState(false);

    const handleClose = () => {
        showModal(false);
    };

    return ([
        <Button variant={"success"} onClick={() => {showModal(true)}}>Dodaj</Button>,

        <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
                <Modal.Title>Nowa książka</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={handleClose}>
                    Zamknij
                </Button>
                <Button variant="primary" onClick={handleClose}>
                    Dodaj
                </Button>
            </Modal.Footer>
        </Modal>
    ]);
}