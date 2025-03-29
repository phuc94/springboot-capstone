import { render, screen } from '@test-utils';
import { Test } from './Test';

describe('Test component', () => {
  it('has correct Vite guide link', () => {
    render(<Test />);
    expect(screen.getByText('this guide')).toHaveAttribute(
      'href',
      'https://mantine.dev/guides/vite/'
    );
  });
});
