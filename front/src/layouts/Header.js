import React, { useState, useEffect } from 'react'
import {Navbar, Nav, Container, Button} from 'react-bootstrap'
import History from '../component/history';
import SearchBar from '../component/SearchBar';


const Header = () => {

  //string은 map을 사용 할 수 없기때문에 object 형태로 변환 시키기 위해 parsing을 해줘야함
  const [keywords, setKeywords] = useState(
    JSON.parse(localStorage.getItem('keywords') || '[]'),
  )

  //keyword에 변화가 일어날때만 랜더링
  useEffect(() => {
    //array 타입을 string형태로 바꾸기 위해 json.stringfy를 사용한다.
    localStorage.setItem('keywords', JSON.stringify(keywords))
  },  [keywords])

  //state를 다루는 함수는 handle 보통 많이 붙인다.

  //검색어 추가
  const handleAddKeyword = (text) => {
    console.log('text', text)
    const newKeyword = {
      id: Date.now(),
      text: text,
    }
    setKeywords([newKeyword, ...keywords])
  }

   //검색어 삭제
   const handleRemoveKeyword = (id) => {
    const nextKeyword = keywords.filter((thisKeyword) => {
      return thisKeyword.id != id
    })
    setKeywords(nextKeyword)
  }

  //검색어 전체 삭제
  const handleClearKeywords = () => {
    setKeywords([])
  }
    return (

            <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
              <div className="container-fluid">
                <a className="navbar-brand" href="#"><font style={{verticalAlign: 'inherit'}}><font style={{verticalAlign: 'inherit'}}>Link</font></font></a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="탐색 전환">
                  <span className="navbar-toggler-icon" />
                </button>
                <div className="collapse navbar-collapse" id="navbarColor01">
                  <ul className="navbar-nav me-auto">
                    <li className="nav-item">
                      <a className="nav-link active" href="#"><font style={{verticalAlign: 'inherit'}}><font style={{verticalAlign: 'inherit'}}>집
                          </font></font><span className="visually-hidden"><font style={{verticalAlign: 'inherit'}}><font style={{verticalAlign: 'inherit'}}>(현재의)</font></font></span>
                      </a>
                    </li>
                    <li className="nav-item">
                      <a className="nav-link" href="#"><font style={{verticalAlign: 'inherit'}}><font style={{verticalAlign: 'inherit'}}>특징</font></font></a>
                    </li>
                    <li className="nav-item">
                      <a className="nav-link" href="#"><font style={{verticalAlign: 'inherit'}}><font style={{verticalAlign: 'inherit'}}>가격</font></font></a>
                    </li>
                    <li className="nav-item">
                      <a className="nav-link" href="#"><font style={{verticalAlign: 'inherit'}}><font style={{verticalAlign: 'inherit'}}>에 대한</font></font></a>
                    </li>
                    <li className="nav-item dropdown">
                      <a className="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false"><font style={{verticalAlign: 'inherit'}}><font style={{verticalAlign: 'inherit'}}>쓰러지 다</font></font></a>
                      <div className="dropdown-menu">
                        <a className="dropdown-item" href="#"><font style={{verticalAlign: 'inherit'}}><font style={{verticalAlign: 'inherit'}}>행동</font></font></a>
                        <a className="dropdown-item" href="#"><font style={{verticalAlign: 'inherit'}}><font style={{verticalAlign: 'inherit'}}>또 다른 행동</font></font></a>
                        <a className="dropdown-item" href="#"><font style={{verticalAlign: 'inherit'}}><font style={{verticalAlign: 'inherit'}}>여기에 다른 것</font></font></a>
                        <div className="dropdown-divider" />
                        <a className="dropdown-item" href="#"><font style={{verticalAlign: 'inherit'}}><font style={{verticalAlign: 'inherit'}}>분리된 링크</font></font></a>
                      </div>
                    </li>
                  </ul>
                  {/* 자식 컴포넌트에서 setState를 못하기때문에 그거를 바꿔주는 함수를 선언후 그 함수를 넘겨야함 */}
                  <div>
                    <SearchBar onAddKeyword={handleAddKeyword}></SearchBar>
                    <History
                      keywords={keywords}
                      onClearKeywords={handleClearKeywords}
                      onRemoveKeyword={handleRemoveKeyword}
                    />
                  </div>
                  <Nav.Link>
                      <Button variant="secondary">Sign In </Button>
                  </Nav.Link>
                  
                  <Nav.Link>
                      <Button variant="secondary">Sign Up </Button>
                  </Nav.Link>
                </div>
              </div>
            </nav>
          );
}

export default Header
