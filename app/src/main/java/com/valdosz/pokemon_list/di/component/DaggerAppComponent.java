// Generated by Dagger (https://dagger.dev).
package com.valdosz.pokemon_list.di.component;

import android.app.Application;
import com.valdosz.pokemon_list.di.module.AppModule;
import com.valdosz.pokemon_list.di.module.AppModule_PokemonAPIFactory;
import com.valdosz.pokemon_list.di.module.AppModule_PokemonRepositoryFactory;
import com.valdosz.pokemon_list.model.api.PokemonAPI;
import com.valdosz.pokemon_list.model.repository.PokemonRepository;
import dagger.internal.Preconditions;

@SuppressWarnings({
        "unchecked",
        "rawtypes"
})
public final class DaggerAppComponent implements AppComponent {
    private final AppModule appModule;

    private DaggerAppComponent(AppModule appModuleParam) {
        this.appModule = appModuleParam;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public void inject(Application app) {
    }

    @Override
    public PokemonAPI pokemonAPI() {
        return AppModule_PokemonAPIFactory.pokemonAPI(appModule);
    }

    @Override
    public PokemonRepository pokemonRepository() {
        return AppModule_PokemonRepositoryFactory.pokemonRepository(appModule, AppModule_PokemonAPIFactory.pokemonAPI(appModule));
    }

    public static final class Builder {
        private AppModule appModule;

        private Builder() {
        }

        public Builder appModule(AppModule appModule) {
            this.appModule = Preconditions.checkNotNull(appModule);
            return this;
        }

        public AppComponent build() {
            Preconditions.checkBuilderRequirement(appModule, AppModule.class);
            return new DaggerAppComponent(appModule);
        }
    }
}
