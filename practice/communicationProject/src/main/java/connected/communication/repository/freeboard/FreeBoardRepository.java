package connected.communication.repository.freeboard;

import connected.communication.repository.Text;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class FreeBoardRepository {
    private static final Map<Long, Text> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    public Text save(Text text){
        text.setId(++sequence);
        store.put(sequence, text);
        return text;
    }

    public Text findByTextId(Long id){
        return store.get(id);
    }

    public void update(Long textId, Text updateText){
        Text findText = findByTextId(textId);

        findText.setTextName(updateText.getTextName());
        findText.setTextContent(updateText.getTextContent());
    }

    public List<Text> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
