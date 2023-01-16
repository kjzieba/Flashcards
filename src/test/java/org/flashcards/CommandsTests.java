package org.flashcards;

import org.flashcards.collection.FlashcardCollectionInterface;
import org.flashcards.collection.ImgFlashcardCollection;
import org.flashcards.collection.TxtFlashcardCollection;
import org.flashcards.commands.*;
import org.flashcards.db.DatabaseProxy;
import org.flashcards.enums.States;
import org.flashcards.states.FlaggedState;
import org.flashcards.states.NormalState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommandsTests {
    private static TxtFlashcardCollection emptyTxtRepoMock;
    private static ImgFlashcardCollection emptyImgRepoMock;

    private static DatabaseProxy emptyDb = DatabaseProxy.getInstance();

    private static TxtFlashcardCollection txtRepoWithCardMock;
    private static ImgFlashcardCollection imgRepoWithCardMock;


    private static TxtCard txtCardMock;
    private static ImgCard imgCardMock;

    private static TxtCard txtCardMockFlaggedState;
    private static ImgCard imgCardMockFlaggedState;

    @BeforeAll
    static void setup() {
        emptyDb = mock(DatabaseProxy.getInstance().getClass());
        when(emptyDb.toString()).thenReturn("DatabaseProxy{memory=[]}");

        emptyTxtRepoMock = mock(TxtFlashcardCollection.class);
        when(emptyTxtRepoMock.getTitle()).thenReturn("Tytuł");
        when(emptyTxtRepoMock.toString()).thenReturn("T 1  []");


        emptyImgRepoMock = mock(ImgFlashcardCollection.class);
        when(emptyImgRepoMock.getTitle()).thenReturn("Tytuł");
        when(emptyImgRepoMock.toString()).thenReturn("I 1  []");

        txtRepoWithCardMock = mock(TxtFlashcardCollection.class);
        when(txtRepoWithCardMock.toString()).thenReturn("T 1  [1|||normal]");


        imgRepoWithCardMock = mock(ImgFlashcardCollection.class);
        when(imgRepoWithCardMock.toString()).thenReturn("I 1  [1|[-119.80.78.71.13.10.26.10.0.0.0.13.73.72.68.82.0.0.2.-64.0.0.1.-116.1.3.0.0.0.-18.-109.-122.38.0.0.0.3.80.76.84.69.-1.-1.-1.-89.-60.27.-56.0.0.0.1.116.82.78.83.0.64.-26.-40.102.0.0.0.57.73.68.65.84.120.94.-19.-64.49.1.0.0.0.-62.32.-5.-89.-74.-58.14.88.24.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.-64.1.-119.-84.0.1.-128.-6.-99.89.0.0.0.0.73.69.78.68.-82.66.96.-126]||normal]");

        txtCardMock = mock(TxtCard.class);
        when(txtCardMock.toString()).thenReturn("1|||normal");
        when(txtCardMock.getAnswer()).thenReturn("Answer2");
        when(txtCardMock.getTextQuestion()).thenReturn("Question");
        when(txtCardMock.getFlashcardState()).thenReturn(new NormalState());

        imgCardMock = mock(ImgCard.class);
        when(imgCardMock.toString()).thenReturn("1|[-119.80.78.71.13.10.26.10.0.0.0.13.73.72.68.82.0.0.2.-64.0.0.1.-116.1.3.0.0.0.-18.-109.-122.38.0.0.0.3.80.76.84.69.-1.-1.-1.-89.-60.27.-56.0.0.0.1.116.82.78.83.0.64.-26.-40.102.0.0.0.57.73.68.65.84.120.94.-19.-64.49.1.0.0.0.-62.32.-5.-89.-74.-58.14.88.24.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.-64.1.-119.-84.0.1.-128.-6.-99.89.0.0.0.0.73.69.78.68.-82.66.96.-126]||normal");
        when(imgCardMock.getAnswer()).thenReturn("Answer");
        when(imgCardMock.getImageQuestion()).thenReturn(imageToBytesArray("src/main/resources/img/panini.jpg"));
        when(imgCardMock.getFlashcardState()).thenReturn(new NormalState());

        txtCardMockFlaggedState = mock(TxtCard.class);
        when(txtCardMockFlaggedState.getFlashcardState()).thenReturn(new FlaggedState());

        imgCardMockFlaggedState = mock(ImgCard.class);
        when(imgCardMockFlaggedState.getFlashcardState()).thenReturn(new FlaggedState());
    }

    @AfterEach
    void afterEach() {
        App.getInstance().getAllCards().deleteFlashcardList(1L);
    }

    @Test
    void shouldReturnTxtRepo_whenExecuteAddRepoCommand() {
        Command command = new AddTxtRepo(1L);
        command.execute();

        FlashcardCollectionInterface flashcardCollectionInterface = App.getInstance().getAllCards().getFlashcardList(1L, "T");

        Assertions.assertEquals(flashcardCollectionInterface.toString(),emptyTxtRepoMock.toString());
    }

    @Test
    void shouldReturnImgRepo_whenExecuteAddRepoCommand() {
        Command command = new AddImgRepo(1L);
        command.execute();

        FlashcardCollectionInterface flashcardCollectionInterface = App.getInstance().getAllCards().getFlashcardList(1L, "I");

        Assertions.assertEquals(flashcardCollectionInterface.toString(),emptyImgRepoMock.toString());
    }

    @Test
    void shouldReturnEmptyAllCardsTxtCase_whenExecuteDelRepoCommand() {

        Command command = new AddTxtRepo(1L);
        command.execute();

        Command command2 = new DelRepo(1L, "T");
        command2.execute();

        Assertions.assertEquals(emptyDb.toString(), App.getInstance().getAllCards().toString());
    }

    @Test
    void shouldReturnEmptyAllCardsImgCase_whenExecuteDelRepoCommand() {

        Command command = new AddImgRepo(1L);
        command.execute();

        Command command2 = new DelRepo(1L, "I");
        command2.execute();

        Assertions.assertEquals(emptyDb.toString(), App.getInstance().getAllCards().toString());
    }


    @Test
    void shouldReturnEmptyTxtCard() {
        App.getInstance().setIdTxtCards(1L);
        TxtCard txtCard = App.getInstance().createEmptyTxtCard();

        Assertions.assertEquals(txtCard.toString(), txtCardMock.toString());
    }

    @Test
    void shouldReturnEmptyImgCard() {
        App.getInstance().setIdImgCards(1L);
        ImgCard imgCard = App.getInstance().createEmptyImgCard();

        Assertions.assertEquals(imgCard.toString(), imgCardMock.toString());
    }

    @Test
    void shouldReturnRightTxtCard_whenExecuteAddTxtCommand() {
        Command command = new AddTxtRepo(1L);
        command.execute();

        App.getInstance().setIdTxtCards(1L);
        TxtCard txtCard = App.getInstance().createEmptyTxtCard();
        Command command1 = new AddTxtCard(1L, txtCard);
        command1.execute();

        App.getInstance().setCurrentRepo(1L);
        FlashcardCollectionInterface flashcardCollectionInterface = App.getInstance().getAllCards().getFlashcardList(1L, "T");

        TxtFlashcardCollection txtFlashcardCollection = (TxtFlashcardCollection) flashcardCollectionInterface;

        TxtCard txtCard1 = txtFlashcardCollection.getTxtFlashcardByID(1L);


        Assertions.assertEquals(txtCard1.toString(), txtCard.toString());

    }

    @Test
    void shouldReturnRightImgCard_whenExecuteAddImgCommand() {
        Command command = new AddImgRepo(1L);
        command.execute();

        App.getInstance().setIdImgCards(1L);
        ImgCard imgCard = App.getInstance().createEmptyImgCard();
        Command command1 = new AddImgCard(1L, imgCard);
        command1.execute();

        App.getInstance().setCurrentRepo(1L);
        FlashcardCollectionInterface flashcardCollectionInterface = App.getInstance().getAllCards().getFlashcardList(1L, "I");

        ImgFlashcardCollection imgFlashcardCollection = (ImgFlashcardCollection) flashcardCollectionInterface;

        ImgCard imgCard1 = imgFlashcardCollection.getImgFlashcardByID(1L);

        Assertions.assertEquals(imgCard1.toString(), imgCard.toString());
    }

    @Test
    void shouldReturnTxtRepoWithRightOneCard_whenExecuteAddTxtCommand() {
        Command command = new AddTxtRepo(1L);
        command.execute();

        App.getInstance().setIdTxtCards(1L);
        TxtCard txtCard = App.getInstance().createEmptyTxtCard();
        Command command1 = new AddTxtCard(1L, txtCard);
        command1.execute();

        App.getInstance().setCurrentRepo(1L);
        FlashcardCollectionInterface flashcardCollectionInterface = App.getInstance().getAllCards().getFlashcardList(1L, "T");
        TxtFlashcardCollection txtFlashcardCollection = (TxtFlashcardCollection) flashcardCollectionInterface;

        Assertions.assertEquals(txtFlashcardCollection.toString(), txtRepoWithCardMock.toString());
    }

    @Test
    void houldReturnImgRepoWithRightOneCard_whenExecuteAddImgCommand() {
        Command command = new AddImgRepo(1L);
        command.execute();

        App.getInstance().setIdImgCards(1L);
        ImgCard imgCard = App.getInstance().createEmptyImgCard();
        Command command1 = new AddImgCard(1L, imgCard);
        command1.execute();

        App.getInstance().setCurrentRepo(1L);
        FlashcardCollectionInterface flashcardCollectionInterface = App.getInstance().getAllCards().getFlashcardList(1L, "T");
        ImgFlashcardCollection imgFlashcardCollection = (ImgFlashcardCollection) flashcardCollectionInterface;

        Assertions.assertEquals(imgFlashcardCollection.toString(), imgRepoWithCardMock.toString());
    }

    @Test
    void shouldReturnEmptyRepo_whenExecuteDelTxtCardCommand() {

        FlashcardTxtHistory flashcardTxtHistory = new FlashcardTxtHistory();

        Command command = new AddTxtRepo(1L);
        command.execute();

        App.getInstance().setIdTxtCards(1L);
        TxtCard txtCard = App.getInstance().createEmptyTxtCard();
        Command command1 = new AddTxtCard(1L, txtCard);
        command1.execute();

        Command command2 = new DelTxtCard(flashcardTxtHistory, 1L, txtCard);
        command2.execute();

        FlashcardCollectionInterface flashcardCollectionInterface = App.getInstance().getAllCards().getFlashcardList(1L, "T");

        Assertions.assertEquals(emptyTxtRepoMock.toString(), flashcardCollectionInterface.toString());
    }

    @Test
    void shouldReturnEmptyRepo_whenExecuteDelImgCardCommand() {
        FlashcardImgHistory flashcardImgHistory = new FlashcardImgHistory();

        Command command = new AddImgRepo(1L);
        command.execute();

        App.getInstance().setIdImgCards(1L);
        ImgCard imgCard = App.getInstance().createEmptyImgCard();
        Command command1 = new AddImgCard(1L, imgCard);
        command1.execute();

        Command command2 = new DelImgCard(flashcardImgHistory, 1L, imgCard);
        command2.execute();

        FlashcardCollectionInterface flashcardCollectionInterface = App.getInstance().getAllCards().getFlashcardList(1L, "I");

        Assertions.assertEquals(emptyImgRepoMock.toString(), flashcardCollectionInterface.toString());
    }


    @Test
    void shouldReturnRightAnswerInImgAfterSet() {
        Command command = new AddImgRepo(1L);
        command.execute();

        App.getInstance().setIdImgCards(1L);
        ImgCard imgCard = App.getInstance().createEmptyImgCard();
        Command command1 = new AddImgCard(1L, imgCard);
        command1.execute();


        FlashcardCollectionInterface flashcardCollectionInterface = App.getInstance().getAllCards().getFlashcardList(1L, "I");
        ImgFlashcardCollection imgFlashcardCollection = (ImgFlashcardCollection) flashcardCollectionInterface;
        imgCard = imgFlashcardCollection.getImgFlashcardByID(1L);
        Command command2 = new ChgAns(imgCard,"Answer2");
        command2.execute();

        Assertions.assertEquals(imgCard.getAnswer(),imgCard.getAnswer());

    }

    @Test
    void shouldReturnRightImageQuestionAfterSet() {
        Command command = new AddImgRepo(1L);
        command.execute();

        App.getInstance().setIdImgCards(1L);
        ImgCard imgCard = App.getInstance().createEmptyImgCard();
        Command command1 = new AddImgCard(1L, imgCard);
        command1.execute();


        FlashcardCollectionInterface flashcardCollectionInterface = App.getInstance().getAllCards().getFlashcardList(1L, "I");
        ImgFlashcardCollection imgFlashcardCollection = (ImgFlashcardCollection) flashcardCollectionInterface;
        imgCard = imgFlashcardCollection.getImgFlashcardByID(1L);
        Command command2 = new ChgImg(imgCard,"src/main/resources/img/panini.jpg");
        command2.execute();

        Assertions.assertEquals(Arrays.toString(imgCard.getImageQuestion()),Arrays.toString(imgCardMock.getImageQuestion()));
    }

    @Test
    void shouldReturnRightTextQuestionAfterSet() {
        Command command = new AddTxtRepo(1L);
        command.execute();

        App.getInstance().setIdTxtCards(1L);
        TxtCard txtCard = App.getInstance().createEmptyTxtCard();
        Command command1 = new AddTxtCard(1L, txtCard);
        command1.execute();


        FlashcardCollectionInterface flashcardCollectionInterface = App.getInstance().getAllCards().getFlashcardList(1L, "T");
        TxtFlashcardCollection txtFlashcardCollection = (TxtFlashcardCollection) flashcardCollectionInterface;
        txtCard = txtFlashcardCollection.getTxtFlashcardByID(1L);
        Command command2 = new ChgQue(txtCard,"Question");
        command2.execute();

        Assertions.assertEquals(txtCard.getTextQuestion(),txtCard.getTextQuestion());
    }

    @Test
    void shouldReturnRightAnswerInTxtAfterSet() {
        Command command = new AddTxtRepo(1L);
        command.execute();

        App.getInstance().setIdTxtCards(1L);
        TxtCard txtCard = App.getInstance().createEmptyTxtCard();
        Command command1 = new AddTxtCard(1L, txtCard);
        command1.execute();


        FlashcardCollectionInterface flashcardCollectionInterface = App.getInstance().getAllCards().getFlashcardList(1L, "T");
        TxtFlashcardCollection txtFlashcardCollection = (TxtFlashcardCollection) flashcardCollectionInterface;
        txtCard = txtFlashcardCollection.getTxtFlashcardByID(1L);
        Command command2 = new ChgAns(txtCard,"Answer");
        command2.execute();

        Assertions.assertEquals(txtCard.getAnswer(),txtCard.getAnswer());
    }

    @Test
    void shouldReturnFlaggedStateInTxtAfterSet() {
        Command command = new AddTxtRepo(1L);
        command.execute();

        App.getInstance().setIdTxtCards(1L);
        TxtCard txtCard = App.getInstance().createEmptyTxtCard();
        Command command1 = new AddTxtCard(1L, txtCard);
        command1.execute();


        FlashcardCollectionInterface flashcardCollectionInterface = App.getInstance().getAllCards().getFlashcardList(1L, "T");
        TxtFlashcardCollection txtFlashcardCollection = (TxtFlashcardCollection) flashcardCollectionInterface;
        txtCard = txtFlashcardCollection.getTxtFlashcardByID(1L);
        Command command2 = new ChgState(txtCard, States.FLAGGED);
        command2.execute();

        Assertions.assertEquals(txtCard.getFlashcardState().toString(),txtCardMockFlaggedState.getFlashcardState().toString());
    }

    @Test
    void shouldReturnNotFlaggedStateInTxtAfterSet() {
        Command command = new AddTxtRepo(1L);
        command.execute();

        App.getInstance().setIdTxtCards(1L);
        TxtCard txtCard = App.getInstance().createEmptyTxtCard();
        Command command1 = new AddTxtCard(1L, txtCard);
        command1.execute();


        FlashcardCollectionInterface flashcardCollectionInterface = App.getInstance().getAllCards().getFlashcardList(1L, "T");
        TxtFlashcardCollection txtFlashcardCollection = (TxtFlashcardCollection) flashcardCollectionInterface;
        txtCard = txtFlashcardCollection.getTxtFlashcardByID(1L);
        Command command2 = new ChgState(txtCard, States.NOT_FLAGGED);
        command2.execute();

        Assertions.assertEquals(txtCard.getFlashcardState().toString(),txtCardMock.getFlashcardState().toString());
    }

    @Test
    void shouldReturnFlaggedStateInImgAfterSet() {
        Command command = new AddImgRepo(1L);
        command.execute();

        App.getInstance().setIdImgCards(1L);
        ImgCard imgCard = App.getInstance().createEmptyImgCard();
        Command command1 = new AddImgCard(1L, imgCard);
        command1.execute();


        FlashcardCollectionInterface flashcardCollectionInterface = App.getInstance().getAllCards().getFlashcardList(1L, "I");
        ImgFlashcardCollection imgFlashcardCollection = (ImgFlashcardCollection) flashcardCollectionInterface;
        imgCard = imgFlashcardCollection.getImgFlashcardByID(1L);
        Command command2 = new ChgState(imgCard,States.FLAGGED);
        command2.execute();

        Assertions.assertEquals(imgCard.getFlashcardState().toString(),imgCardMockFlaggedState.getFlashcardState().toString());

    }

    @Test
    void shouldReturnNotFlaggedStateInImgAfterSet() {
        Command command = new AddImgRepo(1L);
        command.execute();

        App.getInstance().setIdImgCards(1L);
        ImgCard imgCard = App.getInstance().createEmptyImgCard();
        Command command1 = new AddImgCard(1L, imgCard);
        command1.execute();


        FlashcardCollectionInterface flashcardCollectionInterface = App.getInstance().getAllCards().getFlashcardList(1L, "I");
        ImgFlashcardCollection imgFlashcardCollection = (ImgFlashcardCollection) flashcardCollectionInterface;
        imgCard = imgFlashcardCollection.getImgFlashcardByID(1L);
        Command command2 = new ChgState(imgCard,States.NOT_FLAGGED);
        command2.execute();

        Assertions.assertEquals(imgCard.getFlashcardState().toString(),imgCardMock.getFlashcardState().toString());
    }


    @Test
    void shouldReturnRightTitleAfterSetInTxtRepo() {
        Command command = new AddTxtRepo(1L);
        command.execute();

        Command command2 = new ChgTxtTitle(1L,"Tytuł");
        command2.execute();

        FlashcardCollectionInterface flashcardCollectionInterface = App.getInstance().getAllCards().getFlashcardList(1L, "I");
        TxtFlashcardCollection txtFlashcardCollection = (TxtFlashcardCollection) flashcardCollectionInterface;

        Assertions.assertEquals(txtFlashcardCollection.getTitle(),emptyTxtRepoMock.getTitle());
    }

    @Test
    void shouldReturnRightTitleAfterSetInImgRepo() {
        Command command = new AddImgRepo(1L);
        command.execute();

        Command command2 = new ChgImgTitle(1L,"Tytuł");
        command2.execute();

        FlashcardCollectionInterface flashcardCollectionInterface = App.getInstance().getAllCards().getFlashcardList(1L, "I");
        ImgFlashcardCollection imgFlashcardCollection = (ImgFlashcardCollection) flashcardCollectionInterface;

        Assertions.assertEquals(imgFlashcardCollection.getTitle(),emptyImgRepoMock.getTitle());
    }



    static public byte[] imageToBytesArray(String image) {
        File imgPath = new File(image);
        try {
            return Files.readAllBytes(imgPath.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

