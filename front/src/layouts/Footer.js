import React from 'react'
import { Container } from 'react-bootstrap'

const Footer = () => {
    return (
        <footer bg="light" expand="lg" >
               <Container>
                        <div style={{textAlign:"center"}}>
                                &copy; 2021 Linc App. All Rights Reserved
                            </div>   
                </Container> 
        </footer>
    )
}

export default Footer