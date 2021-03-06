package br.com.sasolucoes.yourmarket.category;

import java.util.List;

import android.util.Log;
import br.com.sasolucoes.yourmarket.Repository;

public class CategoryRepository extends Repository {

	private CategoryLocalRepository localRepository = new CategoryLocalRepository();
	private CategoryRemoteRepository remoteRepository = new CategoryRemoteRepository();

	public List<Category> selectAll() {
		synchronizeData();
		
		return localRepository.selectAll();
	}

	private void synchronizeData() {
		if (!isOnline())
			return;
		
		try {
			List<Category> categories = remoteRepository.selectAll();
			localRepository.update(categories);
		} catch (Exception e) {
			Log.e("RemoteRepository", e.getMessage());
		}
	}

}