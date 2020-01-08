import React, {useEffect, useState} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {
    Button,
    ButtonGroup,
    Col,
    Container,
    FormControl,
    InputGroup,
    Jumbotron,
    Row, Table
} from "react-bootstrap";
import {getRequest} from "./SharedKernel/ApiRequest";
import Book from "./Book";
import NewBook from "./NewBook";

function App() {
    const [books, setBooks] = useState([]);

    useEffect(async () => {
        const response = await getRequest('books').then((response) => {
            return response.json();
        });

        setBooks(response);
    }, []);

    const renderBooks = (books) => {
        let lp = 0;
        return books.map((book) => {
            lp += 1;
            return (
                <Book
                    lp={lp}
                    id={book.id}
                    title={book.title}
                    authorName={book.author.name}
                    authorSurname={book.author.surname}
                />
            );
        })
    };

    return (
        <Container>
            <Row>
                <Col xl={12}>
                    <Jumbotron>
                        <h1>Witaj w domowej bibliotece!</h1><br/>
                        <p>
                            <InputGroup className="mb-3">
                                <FormControl
                                    placeholder="Zacznij wpisywać"
                                    aria-label="Wyszukaj"
                                    aria-describedby="basic-addon2"
                                />
                                <InputGroup.Append>
                                    <ButtonGroup>
                                        <FormControl as="select">
                                            <option selected={true} value={1}>Tytuł</option>
                                            <option value={2}>Opis</option>
                                            <option value={3}>Autor</option>
                                            <option value={4}>Wydawca</option>
                                        </FormControl>
                                        <Button variant="success">Wyszukaj!</Button>
                                    </ButtonGroup>
                                </InputGroup.Append>
                            </InputGroup>
                        </p>
                    </Jumbotron>
                </Col>
            </Row>
            <Row>
                <Col xl={12}>
                    <Table striped bordered hover>
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Tytuł</th>
                            <th>Autor</th>
                            <th>
                                <NewBook/>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        {renderBooks(books)}
                        </tbody>
                    </Table>
                </Col>
            </Row>
        </Container>
    );
}

export default App;
