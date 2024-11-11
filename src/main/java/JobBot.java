import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class JobBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "@JobFinder_123bot"; // Название бота
    }

    @Override
    public String getBotToken() {
        return "7934295694:AAHDE7TgB1xYbB0HusLihwwOCCR4EeC02NE"; // Вставьте ваш токен
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String responseText;

            switch (messageText) {
                case "/start":
                    responseText = "Привет! Я бот для поиска работы. Используйте /find_jobs, чтобы начать поиск вакансий.";
                    break;
                case "/find_jobs":
                    responseText = "Введите, пожалуйста, ключевые слова для поиска работы (например, Java разработчик, удаленно).";
                    break;
                case "/subscribe":
                    responseText = "Вы подписаны на уведомления о новых вакансиях. Мы будем уведомлять вас о новых предложениях.";
                    break;
                case "/favorites":
                    responseText = "Ваши избранные вакансии пока пусты. Используйте /find_jobs, чтобы найти подходящие предложения.";
                    break;
                default:
                    responseText = "Извините, я не понял. Пожалуйста, используйте команды /start, /find_jobs, /subscribe, /favorites.";
                    break;
            }

            sendTextMessage(chatId, responseText);
        }
    }

    private void sendTextMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);

        try {
            execute(message); // Отправка сообщения
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
