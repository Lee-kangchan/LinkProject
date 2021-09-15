
import { Container } from "react-bootstrap";
import React, { useState, useEffect } from 'react'
import History from "./component/history";
import SearchBar from "./component/SearchBar";
import {Row} from "react-bootstrap"
import Layout from "./layouts/Layout";
import './index.css';
import LinkCard from "./component/LinkCard";

function App(){
  return <Layout bg="light" className="Container" expand="lg" >
    <Row xs={1} md={5} className="g-4">
        <LinkCard/>
        <LinkCard/>
        <LinkCard/>
        <LinkCard/>
        <LinkCard/>
        <LinkCard/>
        <LinkCard/>
        <LinkCard/>
        <LinkCard/>
        <LinkCard/>
        <LinkCard/>
        <LinkCard/>
        <LinkCard/>
        <LinkCard/>
    </Row>
    </Layout>
}
export default App;