import React from 'react';
import { screen, render, waitFor } from '@testing-library/react';
import { Provider } from 'react-redux';
import { store } from './app/store';

import App from './App';
import AddView from './views/AddView';
import ListView from './views/ListView';

test('renders learn react link', async() => {
  render(
    <Provider store={store}>
      <App />
    </Provider>
  );
  expect(screen.getByText(/list of all people/i)).toBeInTheDocument();

  await waitFor(() => {
    const element = screen.queryByTestId('component-listview');
    expect((element)).toBeInTheDocument();
  });
  const element = screen.queryByTestId('component-listview');
  expect((element?.childElementCount)).toBeGreaterThan(0);
  // const myComponent = screen.queryByTestId('component-listview');
  // expect(myComponent).toBeInTheDocument();
});
