import {Button, ButtonGroup, FormControl, InputGroup} from "react-bootstrap";
import React, {useState} from "react";
import {getRequest} from "./SharedKernel/ApiRequest";

export default function Search(props) {
    const [searchValue, setSearchValue] = useState('');
    const [searchContext, setSearchContext] = useState(1);
    const [searchLoading, setSearchLoading] = useState(false);

    const getSearchContextDecoded = () => {
        switch (searchContext) {
            case 1:
                return 'title';
            case 2:
                return 'desc';
            case 3:
                return 'authorName';
        }
    };

    const search = () => {
        if (searchLoading) {
            return;
        }

        if (searchValue.length < 3) {
            props.refreshBooks();
            return;
        }

        setSearchLoading(true);

        let searchContext = getSearchContextDecoded();
        getRequest('books/search?' + searchContext + "=" + searchValue).then((response) => {
            return response.json();
        }).then((books) => {
            setSearchLoading(false);
            props.setBooks(books);
        });
    };

    return (
        <InputGroup className="mb-3">
            <FormControl
                placeholder="Zacznij wpisywać"
                aria-label="Wyszukaj"
                aria-describedby="basic-addon2"
                onChange={(event) => {
                    setSearchValue(event.target.value);
                }}
                onKeyDown={(event) => {
                    if (event.keyCode === 13) {
                        search();
                    }
                }}
            />
            <InputGroup.Append>
                <ButtonGroup>
                    <FormControl as="select" onChange={(event) => {
                        setSearchContext(event.target.value);
                    }}>
                        <option selected={true} value={1}>Tytuł</option>
                        <option value={2}>Opis</option>
                        <option value={3}>Autor</option>
                    </FormControl>
                    <Button variant="success" onClick={(event) => {search()}}>Wyszukaj!</Button>
                </ButtonGroup>
            </InputGroup.Append>
        </InputGroup>
    );
}