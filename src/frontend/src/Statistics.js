import React, {useEffect, useState} from 'react';
import {Button, Col, Form, Modal, Nav, Table} from "react-bootstrap";
import {getRequest} from "./SharedKernel/ApiRequest";


export default function Statistics() {
    const [show, setShow] = useState(false);
    const [allBooksQty, setAllBooksQty] = useState(0);
    const [booksPerAuthorQty, setBooksPerAuthorQty] = useState([]);

    const handleClose = () => {
        setShow(false);
    };

    const showStatistics = () => {
        setShow(true);

        getRequest('statistics/all-book?mode=ALL_BOOKS').then((response) => {
            return response.json();
        }).then((response) => {
            setAllBooksQty(response.allBooksNumber);
        });

        getRequest('statistics/all-book?mode=BOOKS_PER_AUTHOR').then((respones) => {
            return respones.json();
        }).then((response) => {
            setBooksPerAuthorQty(response.booksPerAuthor);
        });
    };

    return ([
        <Nav.Item>
            <Nav.Link href="#" onClick={showStatistics}>
                Statystyki
            </Nav.Link>
        </Nav.Item>,
        <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
                <Modal.Title>Statystyki</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Table striped bordered hover>
                    <thead>
                    <th>Autor</th>
                    <th>Liczba książek</th>
                    </thead>
                    <tbody>
                    {booksPerAuthorQty.map((book) => {
                        return <tr>
                            <td>{book.name} {book.surname}</td>
                            <td>{book.numberOfBooks}</td>
                        </tr>
                    })}
                    </tbody>
                    <tfoot>
                    <tr>
                        <td colSpan={2}>Sumaryczna liczba książek: {allBooksQty}</td>
                    </tr>
                    </tfoot>
                </Table>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={handleClose}>
                    Zamknij
                </Button>
            </Modal.Footer>
        </Modal>
    ]);
}