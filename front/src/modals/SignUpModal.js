
import React from 'react'
import { Modal, Button, Form, Container } from 'react-bootstrap'
import { GoogleLogin} from "react-google-login"
import HorizonLine from '../component/HorizonLine'
const SignUpModal = ({show, onHide}) => {
    return (
        <Modal 
            show={show} 
            onHide={onHide} 
            size="lg" 
            aria-labelledby="contained-modal-title-vcenter" 
            centered>
            <Container>
                <Modal.Header closeButton>
                    <Modal.Title id="contained-modal-title-vcenter">Sign Up</Modal.Title>

                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group>
                            <Form.Label>Name</Form.Label>
                            <Form.Control placeholder="Enter your name"/>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Password</Form.Label>
                            <Form.Control type= "password" placeholder="Password"/>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Confirm Password</Form.Label>
                            <Form.Control type= "password" placeholder="Confirm Password"/>
                        </Form.Group>
                        <Button block variant="info" type="button" className="my-3">
                            Sign Up
                        </Button>
                        <HorizonLine text={"OR"}/>
                        <GoogleLogin
                            render={renderProps=>{
                                return <Button block onClick={renderProps.onClick} disabled={renderProps.disabled} style={{backgroundColor: "#176BEF", borderColor: '#176BEF'}}>
                                        
                                <i className="fab fa-google"></i> &nbsp; Sign Up with Google
                                </Button>
                            }}
                        ></GoogleLogin>
                    </Form>

                </Modal.Body>
            </Container>
        </Modal>
    )
}
export default SignUpModal;