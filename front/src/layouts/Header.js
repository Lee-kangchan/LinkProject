import React, { useState } from 'react'
import {Navbar, Nav, Container, Button} from 'react-bootstrap'
import SignInModal from '../modals/SignInModal';
import SignUpModal from '../modals/SignUpModal';


const Header = () => {
  const [signUpModalOn, setSignUpModalOn] = useState(false);
  const [signInModalOn, setSignInModalOn] = useState(false);
  //string은 map을 사용 할 수 없기때문에 object 형태로 변환 시키기 위해 parsing을 해줘야함
  // const [keywords, setKeywords] = useState(
  //   JSON.parse(localStorage.getItem('keywords') || '[]'),
  // )

  // //keyword에 변화가 일어날때만 랜더링
  // useEffect(() => {
  //   //array 타입을 string형태로 바꾸기 위해 json.stringfy를 사용한다.
  //   localStorage.setItem('keywords', JSON.stringify(keywords))
  // },  [keywords])

  // //state를 다루는 함수는 handle 보통 많이 붙인다.

  // //검색어 추가
  // const handleAddKeyword = (text) => {
  //   console.log('text', text)
  //   const newKeyword = {
  //     id: Date.now(),
  //     text: text,
  //   }
  //   setKeywords([newKeyword, ...keywords])
  // }

  //  //검색어 삭제
  //  const handleRemoveKeyword = (id) => {
  //   const nextKeyword = keywords.filter((thisKeyword) => {
  //     return thisKeyword.id != id
  //   })
  //   setKeywords(nextKeyword)
  // }

  // //검색어 전체 삭제
  // const handleClearKeywords = () => {
  //   setKeywords([])
  // }
    return (
      <Navbar bg="light" expand="lg">
      <Container>
        <Navbar.Brand href="#home">React-Bootstrap</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link href="#home">Home</Nav.Link>
            <Nav.Link href="#link">Link</Nav.Link>
          </Nav>
        </Navbar.Collapse>
        
        <Navbar.Collapse className="justify-content-end">
          <Navbar.Text>
            Signed in as: <a href="#login">Mark Otto</a>
          </Navbar.Text>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default Header;
