import {Button, Form, Modal} from "react-bootstrap";
import React, {useEffect, useState} from "react";
import NewAuthorButton from "./NewAuthorButton";
import {getRequest, postRequest} from "./SharedKernel/ApiRequest";
import {notify} from "react-notify-toast/bin/notify";

export default function NewBookButton(props) {
    const [show, showModal] = useState(false);
    const [authors, setAuthors] = useState([]);

    const [author, setAuthor] = useState(0);
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [publisher, setPublisher] = useState("");

    const handleClose = () => {
        setTitle("");
        setDescription("");
        showModal(false);
    };

    const refreshAuthors = () => {
        getRequest('/authors').then((response) => {
            return response.json();
        }).then((response) => {
            setAuthor(response[0].id);
            setAuthors(response);
        });
    };

    const addBook = () => {
        if (title.length < 1 || description.length < 1 || publisher.length < 1) {
            notify.show("Uzupełnij wszystkie pola!", "error");
            return;
        }

        postRequest('books', {title: title, description: description, publisher: publisher, author: {id: author}}).then((response) => {
            handleClose();
            notify.show("Książka została dodana!");
            props.refreshBooks();
        });
    };

    useEffect(async () => {
        const response = await getRequest('/authors').then((response) => {
            return response.json();
        });

        setAuthor(response[0].id);
        setAuthors(response);
    }, []);

    return ([
        <Button variant={"success"} onClick={() => {
            showModal(true)
        }}>Dodaj</Button>,

        <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
                <Modal.Title>Nowa książka</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Form.Group controlId="formBasicEmail">
                        <Form.Label>Autor</Form.Label>
                        <select className="form-control" onChange={(event) => {
                            setAuthor(parseInt(event.target.value));
                        }}>
                            {authors.map((author) => {
                                return <option
                                    key={author.id}
                                    value={author.id}
                                >
                                    {author.name} {author.surname}
                                </option>
                            })}
                        </select>
                        <Form.Text className="text-muted">
                            <NewAuthorButton refreshAuthors={refreshAuthors}/>
                        </Form.Text>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Tytuł</Form.Label>
                        <Form.Control type="text" placeholder="Wpisz tytuł książki" value={title} onChange={(event) => {
                            setTitle(event.target.value);
                        }}/>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Opis</Form.Label>
                        <textarea className="form-control" placeholder="Wpisz opis" onChange={(event) => {
                            setDescription(event.target.value);
                        }}>
                            {description}
                        </textarea>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Wydawca</Form.Label>
                        <textarea className="form-control" placeholder="Wpisz wydawcę" onChange={(event) => {
                            setPublisher(event.target.value);
                        }}>
                            {publisher}
                        </textarea>
                    </Form.Group>
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={handleClose}>
                    Zamknij
                </Button>
                <Button variant="primary" onClick={addBook}>
                    Dodaj
                </Button>
            </Modal.Footer>
        </Modal>
    ]);
}