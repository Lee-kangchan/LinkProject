import React from 'react'
import {Card, Button, Col} from 'react-bootstrap'

const LinkCard = () => {
    return (
    <Col classname ="my-3">
    <Card  style={{ width: '80%' , marginLeft: '10%', marginTop:"5%"}}>
        <Card.Img variant="top" src="https://search.pstatic.net/sunny/?src=https%3A%2F%2Fthumbs.dreamstime.com%2Fz%2Fplants-water-ripples-7478275.jpg&type=sc960_832"  />
        <Card.Body>
          <Card.Title>Card Title</Card.Title>
          <Card.Text>
            Some quick example text to build on the card title and make up the bulk of
            the card's content.
          </Card.Text>
        </Card.Body>
      </Card> </Col>
    )
}

export default LinkCard
