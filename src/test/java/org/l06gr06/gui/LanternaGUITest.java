package org.l06gr06.gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.l06gr06.model.Position;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LanternaGUITest {

    private Screen screen;
    private LanternaGUI gui;
    private TextGraphics tg;

    @BeforeEach
    void setUp() {
        screen = mock(Screen.class);
        tg = mock(TextGraphics.class);
        when(screen.newTextGraphics()).thenReturn(tg);
        gui = new LanternaGUI(screen);
    }

    @Test
    void drawNormalPlayer() {
        gui.drawPlayer(0,new Position(20, 20));
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(0, 255, 239));
        Mockito.verify(tg, Mockito.times(1)).putString(20, 23, "&");
    }
    @Test
    void drawImmortalPlayer() {
        gui.drawPlayer(1,new Position(20, 20));
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(255, 153, 0));
        Mockito.verify(tg, Mockito.times(1)).putString(20, 23, "&");
    }
    @Test
    void drawEgg(){
        gui.drawBeast(0,new Position(20, 20));
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(255, 255, 102));
        Mockito.verify(tg, Mockito.times(1)).putString(20, 23, "*");
    }
    @Test
    void drawBeast(){
        gui.drawBeast(1,new Position(20, 20));
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(232, 14, 14));
        Mockito.verify(tg, Mockito.times(1)).putString(20, 23, "H");
    }
    @Test
    void drawStrongerBeast(){
        gui.drawBeast(2,new Position(20, 20));
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(232, 14, 14));
        Mockito.verify(tg, Mockito.times(1)).putString(20, 23, "%");
    }

    @Test
    void drawShield() {
        gui.drawShield(new Position(20, 20));
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(166, 77, 255));
        Mockito.verify(tg, Mockito.times(1)).putString(20, 23, "$");
    }
    @Test
    void drawHeart() {
        gui.drawHeart(new Position(20, 20));
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(252,8,8));
        Mockito.verify(tg, Mockito.times(1)).putString(20, 23, "@");
    }
    @Test
    void drawBlock() {
        gui.drawBlock(new Position(20, 20));
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(new TextColor.RGB(29, 194, 73));
        Mockito.verify(tg, Mockito.times(1)).putString(20, 23, " ");
        Mockito.verify(tg,Mockito.times(1)).putCSIStyledString(20,23,"");
    }

    @Test
    void drawWall() {
        gui.drawWall(new Position(20, 20));
        Mockito.verify(tg, Mockito.times(1)).setBackgroundColor(new TextColor.RGB(255, 255, 102));
        Mockito.verify(tg, Mockito.times(1)).putString(20, 23, " ");
    }

    @Test
    void drawText() {
        gui.drawText(new Position(20, 20), "Beast Game", "#336699");
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(51, 102, 153));
        Mockito.verify(tg, Mockito.times(1)).putString(20,20, "Beast Game");
    }
    @Test
    void clear(){
        gui.clear();
        Mockito.verify(screen,Mockito.times(1)).clear();
    }
    @Test
    void refresh() throws IOException {
        gui.refresh();
        Mockito.verify(screen,Mockito.times(1)).refresh();
    }
    @Test
    void close() throws IOException {
        gui.close();
        Mockito.verify(screen,Mockito.times(1)).close();
    }
    @Test
    void nextActionNone() throws IOException {
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(keyStroke.getKeyType()).thenReturn(KeyType.Character);
        when(keyStroke.getCharacter()).thenReturn('x');
        when(screen.pollInput()).thenReturn(keyStroke);
        Assertions.assertEquals(GUI.ACTION.NONE, gui.getNextAction());
        Assertions.assertNotEquals(gui.getNextAction(),null);
    }

    @Test
    void nextActionNull() throws IOException {
        when(screen.pollInput()).thenReturn(null);
        Assertions.assertEquals(GUI.ACTION.NONE, gui.getNextAction());
    }

    @Test
    void nextActionUp() throws IOException {
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowUp);
        when(screen.pollInput()).thenReturn(keyStroke);
        Assertions.assertEquals(GUI.ACTION.UP, gui.getNextAction());
    }

    @Test
    void nextActionDown() throws IOException {
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowDown);
        when(screen.pollInput()).thenReturn(keyStroke);
        Assertions.assertEquals(GUI.ACTION.DOWN, gui.getNextAction());
    }

    @Test
    void nextActionLeft() throws IOException {
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowLeft);
        when(screen.pollInput()).thenReturn(keyStroke);
        Assertions.assertEquals(GUI.ACTION.LEFT, gui.getNextAction());
    }

    @Test
    void nextActionRight() throws IOException {
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowRight);
        when(screen.pollInput()).thenReturn(keyStroke);
        Assertions.assertEquals(GUI.ACTION.RIGHT, gui.getNextAction());
    }

    @Test
    void nextActionSelect() throws IOException {
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(keyStroke.getKeyType()).thenReturn(KeyType.Enter);
        when(screen.pollInput()).thenReturn(keyStroke);
        Assertions.assertEquals(GUI.ACTION.SELECT, gui.getNextAction());
    }

    @Test
    void nextActionEOF() throws IOException {
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(keyStroke.getKeyType()).thenReturn(KeyType.EOF);
        when(screen.pollInput()).thenReturn(keyStroke);
        Assertions.assertEquals(GUI.ACTION.QUIT, gui.getNextAction());
    }
    @Test
    void nextActionQuit() throws IOException {
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(keyStroke.getKeyType()).thenReturn(KeyType.Character);
        when(keyStroke.getCharacter()).thenReturn('q');
        when(screen.pollInput()).thenReturn(keyStroke);
        Assertions.assertEquals(GUI.ACTION.QUIT, gui.getNextAction());
    }
}

