import React, {useEffect, useState} from 'react';
import Notifications from 'react-notify-toast';
import 'bootstrap/dist/css/bootstrap.min.css';
import {
    Button,
    ButtonGroup,
    Col,
    Container,
    FormControl,
    InputGroup,
    Jumbotron, Nav,
    Row, Table
} from "react-bootstrap";
import {getRequest} from "./SharedKernel/ApiRequest";
import Book from "./Book";
import NewBookButton from "./NewBookButton";
import Search from "./Search";
import Statistics from "./Statistics";

function App() {
    const [books, setBooks] = useState([]);

    useEffect(async () => {
        const response = await getRequest('books').then((response) => {
            return response.json();
        });

        setBooks(response);
    }, []);

    const refreshBooks = () => {
        getRequest('books').then((response) => {
            return response.json();
        }).then((response) => {
            setBooks(response);
        });
    };

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

    const downloadFile = (extension) => {
        window.open("http://homelibrary-env.3wt3cbviwk.eu-central-1.elasticbeanstalk.com/books/download/" + extension, "_blank")
    };

    return (
        <Container>
            <Notifications/>
            <Row>
                <Col xl={12}>
                    <Jumbotron>
                        <h1>Witaj w domowej bibliotece!</h1><br/>
                        <p>
                            <Search setBooks={setBooks} refreshBooks={refreshBooks}/>
                        </p>
                        <p>
                            <Nav variant="pills" defaultActiveKey="#">
                                <Statistics/>

                                <Nav.Item>
                                    <Nav.Link onClick={() => {
                                        downloadFile('csv')
                                    }}>
                                        CSV
                                    </Nav.Link>
                                </Nav.Item>

                                <Nav.Item>
                                    <Nav.Link onClick={() => {
                                        downloadFile('xml')
                                    }}>
                                        XML
                                    </Nav.Link>
                                </Nav.Item>
                            </Nav>
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
                                <NewBookButton refreshBooks={refreshBooks}/>
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
