import React from 'react'
import {Card, Button, Col} from 'react-bootstrap'
import {BrowserRouter as Router, Link, Route} from 'react-router-dom'
import App from '../App'
const LinkCard = () => {
    return (
    <Col classname ="my-3">
    <Card  style={{}} classname="cardDesign">
        <Card.Img variant="top" src="https://search.pstatic.net/sunny/?src=https%3A%2F%2Fthumbs.dreamstime.com%2Fz%2Fplants-water-ripples-7478275.jpg&type=sc960_832"  />
        <Card.Body>
          <Card.Title>Card Title</Card.Title>
          <Card.Text>
            Some quick example text to build on the card title and make up the bulk of
            the card's content.
          </Card.Text>
        </Card.Body>
      </Card>
      
    </Col>
    
    )
}

export default LinkCard
