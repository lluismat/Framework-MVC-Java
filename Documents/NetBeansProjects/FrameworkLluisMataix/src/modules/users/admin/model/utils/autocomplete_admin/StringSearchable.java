package modules.users.admin.model.utils.autocomplete_admin;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import modules.users.admin.model.utils.autocomplete_admin.Searchable;

public class StringSearchable implements Searchable<String,String>{
	private List<String> terms = new ArrayList<String>();

	/**
	 * Constructs a new object based upon the parameter terms. 
	 * @param terms The inventory of terms to search.
	 */

	public StringSearchable(List<String> terms){
            this.terms.addAll(terms);
	}

	@Override
	public Collection<String> search(String value) {
            List<String> founds = new ArrayList<String>();
            for ( String s : terms ){
		if ( s.indexOf(value) == 0 )
                    founds.add(s);
            }
            return founds;
	}
}
