
    Model m_model;
    Personnage c;
    Spell s;
    Ennemi E;
    boss b;
    }


            System.out.println("MouseDragged: (" + e.getX() + "," + e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (Options.ECHO_MOUSE_MOTION)
            System.out.println("MouseMoved: (" + e.getX() + "," + e.getY());
    }

    public void notifyVisible() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}