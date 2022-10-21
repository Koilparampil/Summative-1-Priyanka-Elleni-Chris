import React from 'react'
import "./NavBar.css"
import { Navbar, Container, Nav} from 'react-bootstrap';
const NavBar = () => {
  return (
<Navbar bg="dark" variant="dark" expand={false}>
    <Container fluid>
                <Nav>
                    <Nav.Link  href="/Console">Console  </Nav.Link>
                    <br/>
                    <Nav.Link  href="/Game">Game    </Nav.Link>
                    <br/>
                    <Nav.Link  href="/TShirt">T-Shirt   </Nav.Link>

                </Nav>
    </Container>
</Navbar>
  )
}

export default NavBar